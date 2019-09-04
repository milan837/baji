package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.GameWithMatchListResponsePojo;

public interface MatchesListFragmentContract {
    interface View{
        void displayResponse(GameWithMatchListResponsePojo gameWithMatchListResponsePojo);
    }

    interface Presenter{
        void sendDataToApi();
        void getDataFromApi(GameWithMatchListResponsePojo gameWithMatchListResponsePojo);
    }
}
