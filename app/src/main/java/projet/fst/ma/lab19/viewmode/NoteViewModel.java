package projet.fst.ma.lab19.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import projet.fst.ma.lab19.data.Note;
import projet.fst.ma.lab19.repository.NoteRepository;

public class NoteViewModel
        extends AndroidViewModel {

    private final NoteRepository repository;
    private final LiveData<List<Note>> allNotes;

    public NoteViewModel(
            @NonNull Application application) {

        super(application);

        repository =
                new NoteRepository(application);

        allNotes =
                repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}