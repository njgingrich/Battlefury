package com.nathan.battlefury.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nathan.battlefury.R;
import com.nathan.battlefury.database.DBHelper;
import com.nathan.battlefury.model.Constants;
import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.MatchHistory;
import com.nathan.battlefury.parse.RestClient;
import com.nathan.battlefury.view.MatchRVAdapter;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "matches_fragment";

    private DBHelper datasource;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchesFragment newInstance(String param1, String param2) {
        MatchesFragment fragment = new MatchesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        for (Long match_id : match_ids) {
            RestClient.get().getMatch(Constants.STEAM_KEY, match_id, new Callback<Match>() {
                @Override
                public void success(Match match, Response response) {
                    // success!
                    Log.i("Battlefury", "Match added " + match._id);
                    datasource.insertMatch(match);
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }
        List<Match> matches = datasource.getAllMatches();
        Log.i("MatchesFrag", "added to list: " + matches.size());
        ArrayAdapter<Match> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, matches);
                setListAdapter(adapter);
        setListAdapter(adapter);*/
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        datasource = new DBHelper(getActivity().getApplicationContext());
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.match_frag_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final MatchRVAdapter adapter = new MatchRVAdapter();
        recyclerView.setAdapter(adapter);
        LinkedList<Long> match_list = new LinkedList<>();
        match_list.add(100L);
        match_list.add(200L);
        match_list.add(300L);
        match_list.add(400L);
        adapter.addAll(match_list);

        /*//TODO: This is a shit way of doing it (its just to get it to work)
        //TODO: replace with checking for more matches from user + load old from DB
        // Get all of the user's match IDs
        RestClient.get().getMatchHistory(Constants.STEAM_KEY, 0L, new Callback<MatchHistory>() {
            @Override
            public void success(MatchHistory matchHistory, Response response) {
                Log.i("GetMatchHistory", "Successfully found " + matchHistory.total_matches + " matches for player");
                Log.i("GetMatchHistory", "matches size: " + matchHistory.matches.size());
                adapter.addAll(matchHistory.matches);
                Log.i("MatchesFrag", "adapter size: " + adapter.getItemCount());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        Log.i("MatchesFrag", "adapter size after: " + adapter.getItemCount());*/

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
