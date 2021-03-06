<?php
/*
 * Nが8前後
 * Nの階乗通りの探索をする
 * O(N!)
 * 順列の全探索
 * 順列とはnこのものを順番に並べるのは何通りあるのか考える問題
 * n個すべてを区別できるならn!通りの並べ方がある
 * 爆発的に計算量が増加する
 * n = 10前後の計算に数秒かかる
 *
 * Nが10~20前後
 * 2のN乗通りのbit全探索を行う
 * O(2^N)
 * bit演算をうまく用いるとそれぞれの要素に対して
 * 使うか使わないかの二通があるような
 * 2^N通りの場合を全探索することができる
 * 言い換えれば集合の部分集合をすべて調べ上げることができる
 * Nが大きすぎなければbitDPでO(N^2*2^N)の計算量となるかもしれない
 * 包除原理を使用する問題もある
 *
 * Nが30~40前後
 * 半分全列挙によりO(2^N)の計算量をO(N2^N/2)程度に減らせることがある
 * N個の要素の組み合わせを計算する際
 * N/2ずつ二つのグループに分けてそれぞれ全列挙し
 * 組み合わせ方を高速に求めるという工夫のこと
 * 1.半分のグループAを全列挙
 * 2.もう半分のグループBを全列挙
 * 3.Aのすべての要素について以下を行う
 *	1.Aから選ぶ要素を一つに固定した時条件に合う要素をBから高速に探す
 *
 * Nが50前後
 * O(N^4)程度の計算量なら間に合う
 *
 * Nが300^500前後
 * O(N^3)程度の計算量なら間に合う
 * 区間DPなどがある
 * 区間を表す添え字を持つ動的計画法
 * dp[l][r] := 区間について最適な状況下で何かしらの値
 * 漸化式の更新方法は
 * 区間を更新する際に[l+1, r]と[l, 1-r]などの左右から一つ増減させたものを確認する
 * 区間を更新する際に[l, i]と[i, r]をすべてのiについて確認する
 * がよく見る二種類
 * dp[l][r] = dp[l+1][r]とdp[l][r-1]確認して更新
 * dp[l][r] = 全てのiについてdp[l][i]とdp[l][r]を確認して更新
 * 適用可能な状況
 * 区間の除去、圧縮、合体などが生じるとき
 * ある区間について最適に除去、圧縮、合体した時の値を保持する場合が多い
 * 実際に趣味レーションするとき配列から要素を削除するのは難しいので
 * その場合区間DPが強力
 * O(N^2)が間に合いそうなとき(N<=3000(
 * 一回の更新が定数時間でできる場合
 * dp[l][r] = dp[l+1][r]とdp[l][r-1]から更新
 * O(N^3)が間に合いそうなとき(N<=500)
 * 一回の更新がNに比例した回数でできる場合
 * dp[l][r] = 全てのiについてdp[l][i]とdp[i][r]を確認して更新
 *
 * Nが1000前後
 * O(N^2logN)程度の計算量なら間に合う
 * O(N^4)を半分全列挙で高速化する
 * 一回当たりO(N log N)で判定できる二分探索を用いる
 *
 * Nが3000前後
 * O(N^2)程度の計算量なら間に合う
 *
 * N(10^5)の場合
 * ナイーブに解こうとするとO(N^2)の時間がかかるが工夫によって高速化できる場合が多い
 * O(N log N)でソートするとO(N^2)もかからない
 * 一重ループと二分探索などの組み合わせでO(N log N)で解ける
 * 動的計画法を使ってN*定数サイズの配列を更新することで解ける
 * 二分探索の判定をO(N log N)で行いO(N(log N)^2)でギリ間に合う
 *
 * Nがbit64に収まらない場合
 * 扱う数が10^18よりも大きい場合は桁ごとに考える
 * 文字列で入力を得る
 * 桁DPなどを用いて計算することもある
 * 桁DPは桁ごとに分けて考える動的計画法
 * 1からNまでの整数について条件を満たす数はいくつあるか
 * 1からNまでの整数について条件を満たす最大値は何か
 * という問題で主に使う
 * O(logN)やO(1)でないと計算時間が間に合わない問題で使用する
 * 桁DPではN以下の数をすべて走査することになるので場合分けを頭に入れる
 * N=63435であれば
 * 00000~59999(6*10^4)
 * 60000~62999(3*10^3)
 * 63000~63399(4*10^2)
 * 63400~63429(3*10^1)
 * 63400~63434(5*10^0)
 * 63435(1)
 * dp[i][smaller] := i桁目まで決めたときの暫定の答え
 * ただしsmallerがtrueならNより小さい場合を考えsmallerがfalseならNと同じ場合を考える
 * dp[i][amller]からdp[i+1][smaller]への遷移を考えるときは
 * 以下のようにsmallerのbool値によって分けて考えることができる
 * dp[i][true]からはdp[i+1][true]にのみ遷移
 * i桁目まででNより小さいならi+i桁目をどのように選んでもNより小さい
 * dp[i][false]からdp[i+1][true]へ遷移
 * i桁目までNと同じでi+1桁目はNより小さい数の時
 * dp[i][false]からdp[i][false]へ遷移
 * i桁目までNと同じでi+1桁目もNと同じ数の時34(5*10^0)
 * i桁目までNより小さいものはi+1桁目をどのように選んでもNと同じになることがないので
 * dp[i][true]からdp[i][false]の繊維はありえない
 *
 * 小さめのmodが与えられたとき
 * 比較的小さいmod(数千程度で10^9+7とかではない場合)は剰余を考える
 *
 * 問題文中に意味深な定数がある場合
 * 問題文で何か定数が与えられそれを利用する場合は
 * その数の性質が素数だったり約数に特殊な数があるなどを利用する
 * その数が小さいのでメモリの確保が容易でDPを利用できるなどがある
 *
 * 最初に全探索による解法を考える
 */
