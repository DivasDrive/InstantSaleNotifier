package capstone.msd.conestoga.instantsalenotifier;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import capstone.msd.conestoga.instantsalenotifier.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendMessagingFragment extends Fragment {


    public SendMessagingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_messaging, container, false);

        WebView webView = (WebView) view.findViewById(R.id.webView_sendMessaging);

        webView.getSettings().setJavaScriptEnabled(true) ;
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //Load URL
        webView.loadUrl(Constants.URL_SEND_NOFITICATION);

        webView.setWebViewClient(new WebViewClient());
        return view;
    }

}
