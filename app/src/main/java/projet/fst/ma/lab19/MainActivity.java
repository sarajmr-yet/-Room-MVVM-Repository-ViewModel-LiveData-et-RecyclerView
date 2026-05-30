package projet.fst.ma.lab19;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import projet.fst.ma.lab19.adapter.NoteAdapter;
import projet.fst.ma.lab19.data.Note;
import projet.fst.ma.lab19.viewmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private Button btnAdd;

    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnAdd = findViewById(R.id.btnAdd);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel =
                new ViewModelProvider(this)
                        .get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(
                this,
                notes -> adapter.setNotes(notes)
        );

        btnAdd.setOnClickListener(v -> {

            String title =
                    etTitle.getText().toString().trim();

            String description =
                    etDescription.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Remplir tous les champs",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Note note =
                    new Note(title, description);

            noteViewModel.insert(note);

            etTitle.setText("");
            etDescription.setText("");

            Toast.makeText(
                    MainActivity.this,
                    "Note ajoutée",
                    Toast.LENGTH_SHORT
            ).show();
        });

        adapter.setOnItemLongClickListener(note -> {

            noteViewModel.delete(note);

            Toast.makeText(
                    MainActivity.this,
                    "Note supprimée",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}