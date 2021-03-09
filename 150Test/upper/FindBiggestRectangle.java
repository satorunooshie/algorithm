/*
 * 数百万の単語を持つリストがある時
 * どの行を左から右へ読んでも、どの列を上から下に読んでも、全ての行と列が単語として読めるものになる、
 * 文字の最大(面積が最大)の矩形を作るアルゴリズムを設計する
 * リスト内で連続している単語を選ぶ必要はないが、矩形を作るため、行ごと、列ごとの文字数を揃える必要はある
 */

import java.util.ArrayList;
import java.util.Hashtable;

/*
 * 辞書を使う問題は何らかの前処理を行なって解くことができる
 *
 * 単語で矩形を作ろうと思えば行ごとに同じ長さの単語が必要になり、列ごとにも同様に同じ長さの単語が必要になる
 * そこで、単語の長さごとにグループ分けをしておく
 * 長さiの単語のグループをD[i]とするグループのリストDを用意する
 *
 * 最大の矩形を作ることができるのは単語の長さの二乗
 * int maxRectangle = longestWord * longestWord;
 * for z = maxRectangle to 1 {
 *  for each pair of numbers (i, j) where i * j = z {
 *      // 矩形が作れるか試して、可能ならreturn
 *  }
 * }
 *
 * 矩形の最大から徐々に小さくして調べると、条件を満たす矩形で最初に見つかったものが、
 * 考えうる最大の矩形ということが保証される
 *
 * 幅l、高さhの矩形を作ろうとするメソッドをmakeRectangle(int l, int h)とする
 * その一つとしてh個の単語の順序付き集合に対してループ処理を行い、各列が正しい単語になっているかをチェックする方法がある
 * 動きはするが非効率
 *
 * 正しい単語にならないということを途中で気づいた場合、操作を中断するべき
 * 部分文字列が辞書にある単語の接頭辞かどうか調べるにはトライ木を作る
 * そして行ごとで矩形を作りながら、各列が正しい接頭辞かどうかをチェックするようにする
 * 正しい接頭辞になっていない場合、即座にその先の矩形を作り続けるのをやめる
 *
 * まず単語の長さごとにグループ化の前処理を行う
 * さらに単語長ごとのトライ木の配列を作るがこれは必要になるまで作らない
 * WordGroup[] groupList = WordGroup.createWordGroups();
 * int maxWordLength = groupList.length;
 * Trie[] trieList = new Trie[maxWordLength];
 *
 * maxRectangleは中心的な部分でまず考えうる最大の矩形領域(maxWordLength^2)からスタートして、そのサイズの矩形を作る
 * 失敗したら矩形を一つ小さくして新たに同様の操作を行う
 * 最初にできた矩形が最大サイズ
 * Rectangle maxRectangle() {
 *  int maxSize = maxWordLength * maxWordLength;
 *  for (int z = maxSize; z > 0; z--) {
 *      if (z % i == 0) {
 *          int j = z / i;
 *          if (j <= maxWordLength) {
 *              // 幅i、高さjの矩形を作る(i * j = z)
 *              Rectangle rectangle = makeRectangle(i, j);
 *              if (rectangle != null) {
 *                  return rectangle;
 *              }
 *          }
 *      }
 *  }
 *  return null;
 * }
 *
 * makeRectangleはmaxRectangleから呼ばれて特定の幅と高さの矩形を作ろうとする
 * Rectangle makeRectangle(int length, int height) {
 *  if (groupList[length - 1] == null || groupList[height - 1] == null) {
 *      return null;
 *  }
 *  // まだ作っていなければ単語の長さのトライ木を作る
 *  if (trieList[height - 1] == null) {
 *      LinkedList<String> words = groupList[height - 1].getWords();
 *      trieList[height - 1] = new Trie(words);
 *  }
 *  return makePartialRectangle(length, height, new Rectangle(length));
 * }
 *
 * makePartialRectangleは予定の幅、高さと作りかけの矩形を受け取る
 * 高さが予定の大きさに達していたら、最後に各列の単語が正しいかチェックし、結果を返す
 *
 * 途中の状態では各列が正しい接頭辞になっているかのチェックをする
 * このチェックで正しくない接頭辞が見つかれば正しい矩形を作ることは不可能だから直ちにメソッドを終了する
 * ここでうまくいって、各列も全ての単語の接頭辞になっていれば、現在作りかけの矩形に長さの合う単語を付け加えて再帰的に矩形を作れるか試す
 *
 * Rectangle makePartialRectangle(int l, int h, Rectangle rectangle) {
 *  // 矩形が完成しているか
 *  if (rectangle.height == h) {
 *      if (rectangle.isComplete(1, h, groupList[h - 1])) {
 *          return rectangle;
 *      }
 *      return null;
 *  }
 *  // 列とトライ木を比較して正しい矩形か判定
 *  if (!rectangle.isPartialOK(l, trieList[h - 1])) {
 *      return null;
 *  }
 *  // 長さの合う単語全てを回して、現在作りかけの矩形に単語を加えて、再帰的に矩形が作れるか試す
 *  for (int i = 0; i < groupList[l - 1].length(); i++) {
 *      // 矩形に新しい単語を加えた新しい矩形を作る
 *      Rectangle orgPlus = rectangle.append(groupList[l - 1].getWord(i));
 *      // 新しく作った矩形に対して完全な矩形が作れるかを試す
 *      Rectangle rect = makePartialRectangle(l, h, orgPlus);
 *      if (rect != null) {
 *          return null;
 *      }
 *  }
 *  return null;
 * }
 *
 * Rectangleクラスは単語でできた矩形の一部、あるいは全部を表現し、
 * isPartialOKは矩形の全ての列が単語の接頭辞であるかをチェックするのに呼び出される
 * isCompleteは似たメソッドで各列が完全な単語になっているかチェックする
 */
public class FindBiggestRectangle {
}
class Rectangle {
    public int height, length;
    public char[][] matrix;

    /*
    空の矩形を作る
    幅を固定し単語が加えられるように長さは可変にする
     */
    public Rectangle(int l) {
        height = 0;
        length = l;
    }

    /*
    指定された幅と高さの指定された文字の行列を格納した矩形を作る
    なお引数で指定された幅と高さは引数の配列の次元と一致している必要がある
     */
    public Rectangle(int length, int height, char[][] letters) {
        this.height = letters.length;
        this.length = letters[0].length;
        matrix = letters;
    }
    public char getLetter(int i, int j) {
        return matrix[i][j];
    }
    // TODO:
    /*
    public String getColumn() {}
     */
    /*
    全ての列が正しいか判定する
    行は辞書から直接追加しているため常に正しい
     */
    public boolean isComplete(int l, int h, WordGroup groupList) {
        if (height == h) {
            // 各列が辞書にある単語かどうか判定する
            for (int i = 0; i < l; i++) {
                String col = getColumn(i);
                if (!groupList.containsWord(col)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isPartialOK(int l, Trie trie) {
        if (height == 0) {
            return true;
        }
        for (int i = 0; i < l; i++) {
            String col = getColumn(i);
            if (!trie.contains(col)) {
                return false;
            }
        }
        return true;
    }
    /*
    sを現在の矩形の行に追加した新しい矩形を作る
     */
    // TODO:
    /*
    public Rectangle append(String s) {}
     */
}

/*
 * 特定の長さの単語に対する単純なコンテナ
 * ArrayListだけでなく、ハッシュテーブルにも単語を保持することで検索しやすくする
 */
class WordGroup {
    private Hashtable<String, Boolean> lookup = new Hashtable<String, Boolean>();
    private ArrayList<String> group = new ArrayList<String>();

    public boolean containsWord(String s) {
        return lookup.containsKey(s);
    }

    public void addWord(String s) {
        group.add(s);
        lookup.put(s, true);
    }

    public int length() {
        return group.size();
    }

    public String getWord(int i) {
        return group.get(i);
    }

    public ArrayList<String> getWords() {
        return group;
    }

    public static WordGroup[] createWordGroups(String[] list) {
        WordGroup[] groupList;
        int maxWordLength = 0;
        // 単語の長さの最大値
        for (String s : list) {
            if (s.length() > maxWordLength) {
                maxWordLength = s.length();
            }
        }
        /*
        辞書内の単語を同じ長さの単語ごとにグループ化する
        groupList[i]は長さが(i + 1)の単語のリストになっている
         */
        groupList = new WordGroup[maxWordLength];
        for (String s : list) {
            // 長さが0の単語はないので、wordLengthではなく、wordLength - 1を代わりに使う
            int wordLength = s.length() - 1;
            if (groupList[wordLength] == null) {
                groupList[wordLength] = new WordGroup();
            }
            groupList[wordLength].addWord(s);
        }
        return groupList;
    }
}