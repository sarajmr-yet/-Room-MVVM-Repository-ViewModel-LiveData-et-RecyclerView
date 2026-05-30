package projet.fst.ma.lab19.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

import projet.fst.ma.lab19.data.Note;
import projet.fst.ma.lab19.data.NoteDao;
import projet.fst.ma.lab19.data.NoteDatabase;

public class NoteRepository {

    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {

        NoteDatabase db =
                NoteDatabase.getInstance(application);

        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        Executors.newSingleThreadExecutor()
                .execute(() -> noteDao.insert(note));
    }

    public void delete(Note note) {
        Executors.newSingleThreadExecutor()
                .execute(() -> noteDao.delete(note));
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}