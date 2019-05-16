package com.atguigu.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;



/**
 * 
 * @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜绫荤殑浣滅敤)
 * 
 * 鍦ㄤ俊鍙烽噺涓婃垜浠畾涔変袱绉嶆搷浣滐細
 * acquire锛堣幏鍙栵級 褰撲竴涓嚎绋嬭皟鐢╝cquire鎿嶄綔鏃讹紝瀹冭涔堥�氳繃鎴愬姛鑾峰彇淇″彿閲忥紙淇″彿閲忓噺1锛夛紝
 * 					瑕佷箞涓�鐩寸瓑涓嬪幓锛岀洿鍒版湁绾跨▼閲婃斁淇″彿閲忥紝鎴栬秴鏃躲��
 * release锛堥噴鏀撅級瀹為檯涓婁細灏嗕俊鍙烽噺鐨勫�煎姞1锛岀劧鍚庡敜閱掔瓑寰呯殑绾跨▼銆�
 * 
 * 淇″彿閲忎富瑕佺敤浜庝袱涓洰鐨勶紝涓�涓槸鐢ㄤ簬澶氫釜鍏变韩璧勬簮鐨勪簰鏂ヤ娇鐢紝鍙︿竴涓敤浜庡苟鍙戠嚎绋嬫暟鐨勬帶鍒躲��
 * 
 * 鎯呮櫙锛�3涓仠杞︿綅锛�6閮ㄦ苯杞︿簤鎶㈣溅浣�
 */
public class SemaphoreDemo
{
	public static void main(String[] args)
	{
		Semaphore sp = new Semaphore(3);
		
		//6杈嗚溅浜夋姠3涓溅浣�
		for (int i = 1; i <= 6; i++) {
			new Thread(()->{
				try {
					//鑾峰彇璧勬簮
					sp.acquire();
					System.out.println(Thread.currentThread().getName()+" 鍙疯溅鍋滃叆杞︿綅锛�");
					//鍋滆溅3绉�
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName()+" 鍙疯溅绂诲紑锛�");
					//閲婃斁璧勬簮
					sp.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
