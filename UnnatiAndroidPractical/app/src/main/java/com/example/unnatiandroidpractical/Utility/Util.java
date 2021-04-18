package com.example.unnatiandroidpractical.Utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.unnatiandroidpractical.R;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Util {

    public static ProgressDialog progessDialog;

    /*PROGRESS DIALOG*/
    public static void showProgressDialog(Context context) {

        if (progessDialog == null) {
            progessDialog= new ProgressDialog(context);
            progessDialog.setTitle(context.getString(R.string.app_name));
            progessDialog.setMessage(context.getString(R.string.please_wait));
            progessDialog.setCancelable(false);
            progessDialog.show();
        }
    }

    public static void stopProgressDialog() {
        if (progessDialog != null && progessDialog.isShowing()) {
            progessDialog.dismiss();
            progessDialog = null;
        }
    }

    public static void AlertDialog(final String content, final Activity activity) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.alert_dialogue_view, null);


        TextView title_tv = view.findViewById(R.id.alert_title);
        TextView msg_tv = view.findViewById(R.id.alert_message);
        Button btn_alrt_cancel = view.findViewById(R.id.alert_btn_cancel);
        Button btn_alrt_submit = view.findViewById(R.id.alert_btn_submit);

        btn_alrt_submit.setVisibility(View.GONE);
        btn_alrt_cancel.setText(activity.getString(R.string.ok_value));

        final Dialog dialog = new Dialog(activity, R.style.MaterialDialogSheet);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        msg_tv.setText(content);

        btn_alrt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public static RequestBody GetJsonRequestBody(JSONObject json) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        return body;
    }
}
