package com.tbd.birthdayplanner;

import android.os.AsyncTask;
import android.util.Log;

import com.tbd.birthdayplanner.dto.user.GetUserParticipationsResponse;
import com.tbd.birthdayplanner.utils.UrlUtils;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Get the user planners and update the view accordingly.
 *
 * Created by lb185112 on 22/08/2017.
 */
class GetUserParticipationsTask extends AsyncTask<Void, Void, GetUserParticipationsResponse> {

    private PlanListActivity planListActivity;

    public GetUserParticipationsTask(PlanListActivity planListActivity) {
        this.planListActivity = planListActivity;
    }

    @Override
    protected GetUserParticipationsResponse doInBackground(Void... params) {
        try {
            final String url = UrlUtils.USERS_URL + "/0044567/participations";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            GetUserParticipationsResponse participations = restTemplate.getForObject(url, GetUserParticipationsResponse.class);
            return participations;
        } catch (Exception e) {
            Log.e("PlanListActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(GetUserParticipationsResponse participations) {
        //TextView greetingIdText = (TextView) planListActivity.findViewById(R.id.id_value);
        //greetingIdText.setText(participations.getParticipations().size());
    }
}
