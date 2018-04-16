package wtf.joni.dannouncer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class WebhookListActivity extends AppCompatActivity {

    static final int PICK_WEBHOOK_INFO_REQUEST = 0;

    private List<Webhook> webhookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private WebhooksAdapeter mAdapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webhook_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dannouncer-database").build();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new WebhooksAdapeter(webhookList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        final Intent intent = this.getIntent();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Webhook webhook = webhookList.get(position);
                intent.putExtra("webhookUrl", webhook.getUrl());
                setResult(RESULT_OK, intent);
                finish();
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareWebhookData();

        new Thread(new Runnable() {
            public void run() {
                webhookList.addAll(db.webhookDao().getAll());
            }
        }).start();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Context context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewWebhookActivity.class);
                startActivityForResult(intent, PICK_WEBHOOK_INFO_REQUEST);
            }
        });
    }

    private void prepareWebhookData() {
        Webhook webhook = new Webhook("Test", "url-placeholder");
        webhookList.add(webhook);

        mAdapter.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_WEBHOOK_INFO_REQUEST) {
            if (resultCode == RESULT_OK) {
                String webhookName = data.getStringExtra("webhookName");
                String webhookUrl = data.getStringExtra("webhookUrl");

                final Webhook webhook = new Webhook(webhookName, webhookUrl);
                webhookList.add(webhook);
                new Thread(new Runnable() {
                    public void run() {
                        db.webhookDao().insertAll(webhook);
                    }
                }).start();

                mAdapter.notifyDataSetChanged();
            }
        }
    }

}
