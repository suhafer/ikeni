package id.co.betalogika.ikeni;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Ikeni
{
    public static final int TAG_FEEDBACK = 1;
    public static final int TAG_RATE     = 2;

    private static Ikeni  sIkeni;
    private static Dialog sDialog;

    private Ikeni ()
    {
    }

    public static Ikeni can ()
    {
        if (sIkeni == null)
        {
            sIkeni = new Ikeni();
        }
        return sIkeni;
    }

    public void showExperienceDialog (final Context context)
    {
        sDialog = new Dialog(context);
        LayoutInflater inflater   = LayoutInflater.from(context);
        View           dialogView = inflater.inflate(R.layout.experience_dialog_view, null);
        sDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sDialog.setContentView(dialogView);
        dialogView.findViewById(R.id.choice_yes).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (sDialog.isShowing())
                {
                    sDialog.dismiss();
                }
                showRateDialog(context);
            }
        });
        dialogView.findViewById(R.id.choice_no).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (sDialog.isShowing())
                {
                    sDialog.dismiss();
                }
                showFeedbackDialog(context);
            }
        });
        sDialog.show();
        if (sDialog.getWindow() != null)
        {
            sDialog.getWindow()
                   .setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    private void showFeedbackDialog (final Context context)
    {
        sDialog = new Dialog(context);
        LayoutInflater inflater   = LayoutInflater.from(context);
        View           dialogView = inflater.inflate(R.layout.feedback_dialog_view, null);
        sDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sDialog.setContentView(dialogView);
        dialogView.findViewById(R.id.choice_yes).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (sDialog.isShowing())
                {
                    sDialog.dismiss();
                }
                if (context instanceof DialogInterface)
                {
                    ((DialogInterface) context).acceptListener(TAG_FEEDBACK);
                }
                else
                {
                    showSendFeedbackDialog(context);
                }
            }
        });
        dialogView.findViewById(R.id.choice_no).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (sDialog.isShowing())
                {
                    sDialog.dismiss();
                }
                if (context instanceof DialogInterface)
                {
                    ((DialogInterface) context).refuseListener(TAG_FEEDBACK);
                }
            }
        });
        sDialog.show();
        if (sDialog.getWindow() != null)
        {
            sDialog.getWindow()
                   .setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    private void showRateDialog (final Context context)
    {
        sDialog = new Dialog(context);
        LayoutInflater inflater   = LayoutInflater.from(context);
        View           dialogView = inflater.inflate(R.layout.rate_dialog_view, null);
        sDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sDialog.setContentView(dialogView);
        dialogView.findViewById(R.id.choice_yes).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (sDialog.isShowing())
                {
                    sDialog.dismiss();
                }
                if (context instanceof DialogInterface)
                {
                    ((DialogInterface) context).acceptListener(TAG_RATE);
                }
                else
                {
                    goToPlayStore(context);
                }
            }
        });
        dialogView.findViewById(R.id.choice_no).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (sDialog.isShowing())
                {
                    sDialog.dismiss();
                }
                if (context instanceof DialogInterface)
                {
                    ((DialogInterface) context).refuseListener(TAG_RATE);
                }
            }
        });
        sDialog.show();
        if (sDialog.getWindow() != null)
        {
            sDialog.getWindow()
                   .setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    private void showSendFeedbackDialog (final Context context)
    {
        sDialog = new Dialog(context);
        LayoutInflater inflater   = LayoutInflater.from(context);
        View           dialogView = inflater.inflate(R.layout.send_feedback_dialog_view, null);
        sDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sDialog.setContentView(dialogView);
        final EditText etFeedback = (EditText) dialogView.findViewById(R.id.et_feedback);

        dialogView.findViewById(R.id.choice_yes).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (sDialog.isShowing())
                {
                    sDialog.dismiss();
                }
                if (context instanceof FeedbackInterface)
                {
                    ((FeedbackInterface) context).feedbackListener(etFeedback.getText().toString().trim());
                }
                else
                {
                    Toast.makeText(context, "Please implement FeedbackInterface to handle feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sDialog.show();
        if (sDialog.getWindow() != null)
        {
            sDialog.getWindow()
                   .setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    public void goToPlayStore (Context context)
    {
        final String appPackageName = context.getPackageName();
        try
        {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        }
        catch (android.content.ActivityNotFoundException anfe)
        {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}