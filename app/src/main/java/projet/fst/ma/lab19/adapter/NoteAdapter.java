package projet.fst.ma.lab19.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import projet.fst.ma.lab19.R;
import projet.fst.ma.lab19.data.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();

    private OnItemLongClickListener listener;

    public interface OnItemLongClickListener {
        void onItemLongClick(Note note);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note,
                        parent,
                        false);

        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(
            @NonNull NoteHolder holder,
            int position) {

        Note note = notes.get(position);

        holder.txtTitle.setText(note.getTitre());
        holder.txtDescription.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final TextView txtDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            itemView.setOnLongClickListener(v -> {

                int position = getAdapterPosition();

                if (listener != null &&
                        position != RecyclerView.NO_POSITION) {

                    listener.onItemLongClick(
                            notes.get(position));
                }

                return true;
            });
        }
    }
}