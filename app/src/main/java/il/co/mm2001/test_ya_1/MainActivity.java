package il.co.mm2001.test_ya_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import il.co.mm2001.model.Picture;
import il.co.mm2001.model.Pictures;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvImage;
    private CharactersAdapter charactersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
        setRecycleView();
    }

    private void setRecycleView() {
        CharactersAdapter.OnItemClickListener listener = new CharactersAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Picture picture) {
                if (picture.isPictureSelected()){
                    picture.setPictureSelected(false);
                }
                else{
                    picture.setPictureSelected(true);
                }
            }
        };
        charactersAdapter = new CharactersAdapter(MainActivity.this, R.layout.character_single_layout, listener);
        rvImage.setAdapter(charactersAdapter);
        rvImage.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializeViews() {
        rvImage = (RecyclerView)findViewById(R.id.rvImage);
    }
}