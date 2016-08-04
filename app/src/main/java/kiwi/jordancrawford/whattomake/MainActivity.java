package kiwi.jordancrawford.whattomake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the recycler view.
        recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        // Setup a linear layout manager.
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Setup our adapter.
        String[] data = new String[3];
        data[0] = "Bananas";
        data[1] = "Cake";
        data[2] = "Yoghurt";
        recyclerViewAdapter = new MealListAdapter(data);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
