package com.hfad.stopwatch.chapter_14_Tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hfad.stopwatch.R
import com.hfad.stopwatch.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        /*Строит базу данных (если она еще не существует) и
          получает ссылку на свойство taskDao.*/
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[TasksViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter()
        val adapter16 = TaskItemAdapter16List {taskId ->
            /*Toast.makeText(context, "Clicked task $taskId", Toast.LENGTH_LONG).show()*/
            viewModel.onTaskClicked(taskId)
        }
        binding.tasksList.adapter = adapter16
        /*binding.tasksList.adapter = adapter*/

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter16.submitList(it)
                /*adapter.data = it*/
            }
        })

        /*когда пользователь кликает на элемент recyclerView вызывается метод viewModel.onTaskClicked(taskId)
        * который передает значение свойству _navigateToTask VM, наш фрагмент наблюдает за этим свойством, и
        * когда в LD появляется значение выполняется код перехода в следующий фрагмент и зывается viewModel.onTaskNavigation()
        * который обнуляет значение в LD  */
        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {
                val action = TasksFragmentDirections.actionTasksFragmentToEditTextFragment(taskId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigation()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}