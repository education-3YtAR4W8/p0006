# 入力

- 逆ポーランド記法の計算式

# 出力

- 中置記法に変換した計算式
- 計算結果

# これでテストしてみる

## 単体の演算子

- 1 + 2
- 4 - 3
- 2 * 4
- 4 / 2

## カッコ有無

- 1 + 2 * 3
- (1 + 2) * 3
- 2 * 3 + 1
- 2 * (3 + 1)
- 6 - 4 / 2
- (6 - 4) / 2
- 4 / 2 - 6
- 4 / (6 - 2)
- 4 / 2 * (6 / 3)

## 複雑な計算

(4 + 6) * (5 - 2) / ((1 + 2) * (3 + 2 * 1))
= 2

逆ポーランド記法
4 6 + 5 2 - * 1 2 + 3 2 1 * + * /
