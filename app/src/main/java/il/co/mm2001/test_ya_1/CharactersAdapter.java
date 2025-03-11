package il.co.mm2001.test_ya_1;

import static androidx.core.app.NotificationCompat.getColor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodel.ViewModelSelectedPositions;

import java.util.HashSet;
import java.util.Set;

import il.co.mm2001.model.Picture;
import il.co.mm2001.model.Pictures;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.PictureViewHolder>{
    private Context context;
    private Pictures pictures;
    private int single_layout;
    private OnItemClickListener listener;
    private Set<Integer> selectedPositions = new HashSet<>();

    public interface OnItemClickListener {
        public void onItemClicked(Picture picture);
    }
    public CharactersAdapter(Context context, int single_layout,
                        OnItemClickListener listener){
        this.listener = listener;
        this.context = context;
        this.pictures = new Pictures();
        this.single_layout = single_layout;
    }
    @NonNull
    @Override
    public CharactersAdapter.PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(single_layout, parent, false);
        return new PictureViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CharactersAdapter.PictureViewHolder holder, int position) {
        Picture picture = pictures.get(position);

        if (picture != null){
            holder.bind(picture, listener);
        }

        if (selectedPositions.contains(position))
            holder.itemView.setBackgroundColor(Color.YELLOW);
        else
            holder.itemView.setBackgroundColor(Color.WHITE);

        holder.itemView.setOnClickListener(v -> {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position); // Deselect if it's already selected
            } else {
                selectedPositions.add(position); // Select if it's not selected
            }
            notifyItemChanged(position); // Notify that the item state has changed
        });
    }

    @Override
    public int getItemCount() {
        return (pictures == null) ? 0 : pictures.size();
    }

    public void saveSelectedPositions(Set<Integer> selectedPositions) {
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Convert the set of integers to a comma-separated string
        StringBuilder selectedPositionsString = new StringBuilder();
        for (Integer position : selectedPositions) {
            selectedPositionsString.append(position).append(",");
        }

        // Remove the trailing comma if there is one
        if (selectedPositionsString.length() > 0) {
            selectedPositionsString.setLength(selectedPositionsString.length() - 1);
        }

        // Save the string in SharedPreferences
        editor.putString("selected_positions", selectedPositionsString.toString());
        editor.apply();
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPicture;
        public PictureViewHolder (@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }
        @SuppressLint("SetTextI18n")
        public void bind(Picture picture, OnItemClickListener listener){
            ivPicture.setImageResource(ivPicture.getContext().getResources().getIdentifier(picture.getName(),
                    "drawable", ivPicture.getContext().getPackageName()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(picture);
                    if (!picture.isPictureSelected())
                        view.setBackgroundColor(Color.YELLOW);
                    else
                        view.setBackgroundColor(Color.WHITE);
                }
            });
        }
    }
}
