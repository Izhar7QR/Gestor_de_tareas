package com.example.gestordetareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAddTask;
    private LinearLayout layoutCheckboxTasks;
    private LinearLayout layoutCompletedTasks;
    private ArrayList<CheckBox> checkboxTasks;
    private ArrayList<TextView> completedTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los elementos de la interfaz
        editTextTask = findViewById(R.id.edit_text_task);
        buttonAddTask = findViewById(R.id.button_add_task);
        layoutCheckboxTasks = findViewById(R.id.layout_checkbox_tasks);
        layoutCompletedTasks = findViewById(R.id.layout_completed_tasks);

        // Crear una lista para almacenar los checkbox de las tareas y las tareas completadas
        checkboxTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();

        // Agregar un Listener al botón para agregar una nueva tarea
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskText = editTextTask.getText().toString().trim();
                if (!taskText.isEmpty()) {
                    addTask(taskText);
                }
            }
        });
    }

    private void addTask(String taskText) {
        // Crear un nuevo checkbox para la tarea
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(taskText);

        // Agregar el checkbox a la lista y al layout
        checkboxTasks.add(checkBox);
        layoutCheckboxTasks.addView(checkBox);

        // Agregar un
        // Listener al checkbox para detectar cuándo se marca o desmarca
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // Crear un nuevo TextView para la tarea completada
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(taskText);

                    // Agregar el TextView a la lista y al layout
                    completedTasks.add(textView);
                    layoutCompletedTasks.addView(textView);

                    // Remover el checkbox de la lista y del layout
                    checkboxTasks.remove(checkBox);
                    layoutCheckboxTasks.removeView(checkBox);
                } else {
                    // Buscar y remover el TextView correspondiente en la lista y en el layout
                    for (TextView textView : completedTasks) {
                        if (textView.getText().toString().equals(taskText)) {
                            completedTasks.remove(textView);
                            layoutCompletedTasks.removeView(textView);
                            break;
                        }
                    }

                    // Agregar el checkbox de vuelta a la lista y al layout
                    checkboxTasks.add(checkBox);
                    layoutCheckboxTasks.addView(checkBox);
                }
            }
        });

        // Limpiar el EditText
        editTextTask.setText("");
    }
}