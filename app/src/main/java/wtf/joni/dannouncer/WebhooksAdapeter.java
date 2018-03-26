package wtf.joni.dannouncer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Joni Mettälä on 17-Mar-18.
 */

public class WebhooksAdapeter extends RecyclerView.Adapter<WebhooksAdapeter.MyViewHolder> {
    private List<Webhook> webhookList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView webhookName, webhookUrl;

        public MyViewHolder(View view) {
            super(view);
            webhookName = (TextView) view.findViewById(R.id.webhook_name);
            webhookUrl = (TextView) view.findViewById(R.id.webhook_url);
        }
    }

    public WebhooksAdapeter(List<Webhook> webhookList) {
        this.webhookList = webhookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.webhook_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Webhook webhook = webhookList.get(position);
        holder.webhookName.setText(webhook.getName());
        holder.webhookUrl.setText(webhook.getUrl());
    }

    @Override
    public int getItemCount() {
        return webhookList.size();
    }
}
