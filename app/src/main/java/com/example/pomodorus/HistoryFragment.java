package com.example.pomodorus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pomodorus.Adapters.Session;
import com.example.pomodorus.Adapters.SessionsAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Session> sessionArrayList;
    private SessionsAdapter sessionsAdapter;
    private FirebaseFirestore db;
    private Button updateList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateList = view.findViewById(R.id.update_list);

        db = FirebaseFirestore.getInstance();

        sessionArrayList = new ArrayList<Session>();
        sessionsAdapter = new SessionsAdapter(getActivity(), sessionArrayList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llm);



        updateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventChangeListener();
                recyclerView.setAdapter(sessionsAdapter);
            }
        });


        return view;

    }

    private void EventChangeListener() {

        db.collection("Sessions")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        assert value != null;
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                sessionArrayList.add(dc.getDocument().toObject(Session.class));
                            }
                        }
                    }
                });
    }
}