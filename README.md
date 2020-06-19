Game TicTacToe for two players or one (against computer).
Computer always plays toe.
Algoritm for computer: it is checking value of every cell on field.
All empty cells are filled by 6 (to initiate, that they are empty), 1 for toe, and 0 for cross
Examples of value of combinations:
999999 — 11111 — five in a row
70000 — 611116 — open four
40000 — 61111 — half closed four
20000 — 616111, 611611, 611161 — half closed three with a gap
30000 — 61116 — open three
15000 — 6111 — half closed three
8000 — 61161, 61611 — half closed three with a gap
2000 — 6116 open two
Every cell has random additional price that changes every turn from 1 to 400 to do turns of computer random
For example we have combination 61116 (open three), the value will be placed for cells with 6.
First cell will costs 40400 (70000(open 4) - 30000(open 3) + 400(random)), fifth cell will costs 40001(random = 1),
so the computer will choose first cell to place the toe.
