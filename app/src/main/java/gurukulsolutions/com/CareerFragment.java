package gurukulsolutions.com;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import gurukulsolutions.com.Model.Products;
import gurukulsolutions.com.ViewHolder.DisplayViewHolder;


public class CareerFragment extends Fragment {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference ProductRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_career, container, false );
        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_career);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ProductRef=FirebaseDatabase.getInstance().getReference().child("Career");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Products> options=
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductRef,Products.class)
                        .build();

        FirebaseRecyclerAdapter<Products,DisplayViewHolder> adapter=
                new FirebaseRecyclerAdapter<Products, DisplayViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull DisplayViewHolder holder, int position, @NonNull final Products model)
                    {


                        holder.txtProductName.setText(model.getPname());
                        Picasso.get().load(model.getImage()).into(holder.imageView);


                    }

                    @NonNull
                    @Override
                    public DisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.display,parent,false);
                        DisplayViewHolder holder=new DisplayViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
