package com.example.trip.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.trip.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.MailShareContent;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.sso.EmailHandler;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

public class MainActivity extends Activity implements OnClickListener {
	private final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");
	private SHARE_MEDIA mPlatform = SHARE_MEDIA.SINA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn).setOnClickListener(this);
		// 配置需要分享的相关平台
		configPlatforms();
		// 设置分享的内容
		setShareContent();
	}

	/**
	 * 根据不同的平台设置不同的分享内容</br>
	 */
	private void setShareContent() {
		// 配置SSO
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this,
				"1104919652", "CwDPopJGllSeLgFl");
		qZoneSsoHandler.addToSocialSDK();
		mController.setShareContent("走知旅游，带你走遍天下。");
		UMImage localImage = new UMImage(this, R.drawable.logs);
		// UMImage urlImage = new UMImage(this,
		// "http://www.umeng.com/images/pic/social/integrated_3.png");

		// UMusic uMusic = new UMusic(
		// "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
		// uMusic.setAuthor("umeng");
		// uMusic.setTitle("天籁之音");
		// uMusic.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");

		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareContent("走知旅游，带你走遍天下。");
		weixinContent.setTitle("友盟社会化分享组件-微信");
		weixinContent.setTargetUrl("http://www.umeng.com/social");
		// weixinContent.setShareMedia(urlImage);
		mController.setShareMedia(weixinContent);
		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareContent("走知旅游，带你走遍天下。");
		circleMedia.setTitle("走知");
		// circleMedia.setShareMedia(urlImage);
		circleMedia.setTargetUrl("http://www.umeng.com/social");
		mController.setShareMedia(circleMedia);

		UMImage image = new UMImage(this, BitmapFactory.decodeResource(
				getResources(), R.drawable.logs));
		image.setTitle("走知");
		image.setThumb("http://www.umeng.com/images/pic/social/integrated_3.png");

		UMImage qzoneImage = new UMImage(this,
				"http://www.umeng.com/images/pic/social/integrated_3.png");
		qzoneImage
				.setTargetUrl("http://www.umeng.com/images/pic/social/integrated_3.png");

		// 设置QQ空间分享内容
		QZoneShareContent qzone = new QZoneShareContent();
		qzone.setShareContent("走知旅游，带你走遍天下。");
		qzone.setTargetUrl("http://www.umeng.com");
		qzone.setTitle("走知");
		mController.setShareMedia(qzone);

		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setShareContent("走知旅游，带你走遍天下。");
		qqShareContent.setTitle("走知");
		qqShareContent.setShareMedia(image);
		qqShareContent.setTargetUrl("http://www.umeng.com/social");
		mController.setShareMedia(qqShareContent);

		// 视频分享
		UMVideo umVideo = new UMVideo(
				"http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
		umVideo.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
		umVideo.setTitle("走知");

		TencentWbShareContent tencent = new TencentWbShareContent();
		tencent.setShareContent("走知旅游，带你走遍天下。");
		// 设置tencent分享内容
		mController.setShareMedia(tencent);

		SinaShareContent sinaContent = new SinaShareContent();
		sinaContent.setShareContent("走知旅游，带你走遍天下。");
		sinaContent.setShareImage(new UMImage(this,
				R.drawable.actionbar_back_indicator));
		mController.setShareMedia(sinaContent);

	}

	/**
	 * 添加短信平台</br>
	 */

	/**
	 * 配置分享平台参数</br>
	 */
	private void configPlatforms() {
		// 添加新浪SSO授权
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// 添加腾讯微博SSO授权
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		// 添加QQ、QZone平台
		addQQQZonePlatform();
		// 添加微信、微信朋友圈平台
		addWXPlatform();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mController.getConfig().cleanListeners();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		UMSsoHandler ssoHandler = SocializeConfig.getSocializeConfig()
				.getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}

	/**
	 * @功能描述 : 添加微信平台分享
	 * @return
	 */
	private void addWXPlatform() {
		// 注意：在微信授权的时候，必须传递appSecret
		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appId = "wx96550d3e625df6ba";
		String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(this, appId, appSecret);
		wxHandler.addToSocialSDK();

		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(this, appId, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	/**
	 * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
	 *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
	 *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
	 *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
	 * @return
	 */
	private void addQQQZonePlatform() {
		String appId = "1104919652";
		String appKey = "CwDPopJGllSeLgFl";
		// 添加QQ支持, 并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, appId, appKey);
		qqSsoHandler.setTargetUrl("http://www.umeng.com/social");

		qqSsoHandler.addToSocialSDK();
		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, appId,
				appKey);
		qZoneSsoHandler.addToSocialSDK();
	}

	/**
	 * 点击事件处理。</br> 在不考虑是否授权的情况下区别openShare、postShare、directShare三个方法 <li>
	 * openShare : 分享面板 -> 分享编辑页 -> 分享 <li>postShare : 分享编辑页 -> 分享 <li>
	 * directShare : 分享
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btn) {
			mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
					SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,
					SHARE_MEDIA.QZONE);
			mController.openShare(this, false);
		}
	}

	/**
	 * 调用postShare分享。跳转至分享编辑页，然后再分享。</br> [注意]<li>
	 * 对于新浪，豆瓣，人人，腾讯微博跳转到分享编辑页，其他平台直接跳转到对应的客户端
	 */
	private void postShare() {
		CustomShareBoard shareBoard = new CustomShareBoard(this);
		shareBoard.showAtLocation(this.getWindow().getDecorView(),
				Gravity.BOTTOM, 0, 0);
	}

}
