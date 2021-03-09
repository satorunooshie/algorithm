import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 高橋君と青木君が野球をしています。高橋君はピッチャー、青木君はバッターです。
 * 高橋君は消える魔球を投げることができます。高橋君が投げる消える魔球は、速さ
 * Vm/sで等速直線運動をし、投げた瞬間からT秒後からS秒後まで (両端を含む) 消えています。消えている間もボールは移動を続けます。
 * ボールが高橋君のもとからちょうどDm離れたときにボールが消えていないならば、青木君はボールを打つことができます。消えているなら打つことはできません。
 * 青木君は高橋君のボールを打つことができますか?
 * 標準入力
 * V S T D
 */
public class VanishingPitch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] parts = br.readLine().split(" ");
        int V = Integer.parseInt(parts[0]);
        int T = Integer.parseInt(parts[1]);
        int S = Integer.parseInt(parts[2]);
        int D = Integer.parseInt(parts[3]);

        double time = D / (double)V;

        if (time >= T && time <= S) {
            bw.write("No\n");
        } else {
            bw.write("Yes\n");
        }
        br.close();
        bw.close();
    }
}