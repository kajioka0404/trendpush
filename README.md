# Trendpush利用方法

## 仕様
日本版yahooのTwiiterトレンドを定期的に検索し、事前に設定したキーワードがトレンド入りしている場合にSMSを送信します。

## 使用方法
1. Twilioのサイトより、開発者用アカウントを取得します。
1. 上記で取得したSID,AuthToken,送信元番号,送信先番号を環境変数に登録します。<br>
  Twilio_SID：SID<br>
  Twilio_auth：AuthToken<br>
  Twilio_toNumber：自分の携帯番号<br>
  Twilio_fromNumber：取得した送信元番号<br>
1. app.propertiesの定義内容を修正します。<br>
  keyword：検索キーワード<br>
  longdelaytime：キーワード検出後、次回検索までのdelay[ms]<br>
  delaytime：キーワード未検出時の次回検索までのdelay[ms]
1. ビルド実行
1. デプロイ
