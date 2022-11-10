基本的にこちらの資料から引用 https://www.ipa.go.jp/files/000017316.pdf

# 概要
特定のページに対して、罠ページを上から配置し、視覚的に騙してユーザーが意図しない機能を実行させられること。
視覚的に騙して行う攻撃なので影響はクリック操作でできる範囲となる。



<img width="583" alt="image" src="https://user-images.githubusercontent.com/44897118/200705221-92311d81-425f-4a1a-9034-d38f0f6da51e.png">


アプリケーションのバグではなくHTMLの仕様を悪用した攻撃方法。

## 具体例

**罠ページ**
→攻撃者が用意したサイト

**サイトA**
→twitter的なサービスとする。投稿にはログインが必要で、ユーザーはすでにログイン済みの状態。

ユーザーの興味を引くようなボタンが配置されている罠ページがある。
<img width="588" alt="image" src="https://user-images.githubusercontent.com/44897118/200705248-1b0e01a6-e414-4000-882c-d81c9058922a.png">


ボタンの上に、ユーザーから見えないようにiframeでサイトAが被さっている。
<img width="588" alt="image" src="https://user-images.githubusercontent.com/44897118/200705268-88e80876-ea32-44e3-8582-b04e2839d39e.png">


罠ページのボタンと、見ない状態のサイトAのボタンの位置が被っている。そのためユーザーは罠サイトのボタンをクリックしたつもりがサイトAのボタンをクリックしてしまう。
<img width="588" alt="image" src="https://user-images.githubusercontent.com/44897118/200705298-1220561c-5f36-494f-b68e-7a78edceadae.png">


被さっているボタンが投稿ボタンでテキストを用意されていれば攻撃者の任意の投稿をさせられてしまう。


## 対策
HTTPレスポンスヘッダでiframeを使えないように設定する。

https://developer.mozilla.org/ja/docs/Web/HTTP/Headers/X-Frame-Options


<img width="589" alt="image" src="https://user-images.githubusercontent.com/44897118/200705322-8bfee496-8d29-4473-a1b3-a446176564f6.png">

上記オプションがついているとレスポンス自体は200で帰ってくるが、ページ内に描画しないようブラウザが動いてくれる。


### ktorの場合

DefaultHeadersで設定できる。
https://ktor.io/docs/default-headers.html#add_dependencies

```
install(DefaultHeaders) {
        header("X-Frame-Options", "DENY")
    }
```

先ほどと同じアプリケーションに上記の設定を入れる。
<img width="592" alt="image" src="https://user-images.githubusercontent.com/44897118/200705359-cac1187c-9909-4e04-a8c2-67d4b1559579.png">



iframeが許可されてないのでサイトAが表示されなくなる。
<img width="590" alt="image" src="https://user-images.githubusercontent.com/44897118/200705391-6c10f8ae-bbc4-4ab3-83f6-c983dab7f283.png">
<img width="587" alt="image" src="https://user-images.githubusercontent.com/44897118/200705415-ee8d2ec1-0902-4949-8252-dca8000b36be.png">

基本的にクリックジャッキングに関してはX-Frame-Optionsを設定すれば防げる。

参照
セキュリティとヘッダー
<img width="986" alt="image" src="https://user-images.githubusercontent.com/44897118/200705718-8ecacec7-89d6-4a40-a739-6ab35568e280.png">
https://spring.pleiades.io/spring-security/reference/features/exploits/headers.html



