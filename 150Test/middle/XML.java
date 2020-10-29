/*
   XMLは非常に冗長なためタグをあらかじめ定義された整数値にマップすることでコード化されている
   Element -> Tag Attributes END Children END
   Attribute -> Tag Value
   END -> 0
   Tag -> 事前に定義されたint値へのマップ
   Value -> 文字列値 END
   例えば次のようなXMLデータは以下のように圧縮されます
   <family lastName="McDowell" state="CA">
   <person firstName="Gayle">Some Message</person>
   </family>
   変換後
   1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0.
   このようにXMLデータを変換しそれを表示するコードを書いてください
*/
/*
   Element, Attributeオブジェクトとしてデータが渡されるのでコードはシンプルで木構造のような考え方をする
   XMLデータに対してencode()を繰り返し呼び出し、XMLの要素のタイプごとに異なる方法で取り扱う
*/
public static void encode(Element root, StringBuffer sb) {
    encode(root.getNameCode(), sb);
    for (Attribute a : root.attributes) {
        encode(a, sb);
    }
    encode("0", sb);
    if (root.value != null && root.value != "") {
        encode(root.value, sb);
    } else {
        for (Element e : root.children) {
            encode(e, sb);
        }
    }
    encode("0", sb);
}
//文字列に空白文字をつけて書式が崩れるのを防ぐ
public static void encode(String v, StringBuffer sb) {
    sb.append(v);
    sb.append(" ");
}
public static void encode(Attribute attr, StringBuffer sb) {
    encode(attr.getTagCode(), sb);
    encode(attr.value, sb);
}
public static String encodeToString(Element root) {
    StringBuffer sb = new StringBuffer();
    encode(root, sb);
    return sb.toString();
}
