# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

ord() {
    echo `expr index "ABCDEFGHIJKLMNOPQRSTUVWXYZ" $1`
}

IFS=$'\n'
read L
read H
read T
T=${T^^}
ROWS=()
for (( i=0; i<H; i++ )); do
    read -r ROW
    ROWS[$i]=$ROW
    # echo ${ROWS[$i]}
done

OUTPUT=()
for (( i=0; i<${#T}; i++)); do
    char=${T:$i:1}
    ind=$(ord $char)
    if [ $ind -lt 0 ]; then
        ind=26;
    else
        ind=$(($ind - 1))
    fi
    # echo $index
    for (( j=0; j<H; j++ )); do
        append=${ROWS[$j]:$(($ind * $L)):$L}
        # echo "$j $append"
        if [ $i -eq 0 ]; then
            OUTPUT[$j]=$append
        else
            OUTPUT[$j]="${OUTPUT[$j]}$append"
        fi
        # echo "${OUTPUT[0]}"
        # OUTPUT[$j]="$OUTPUT[$j]${ROWS[$j]:`eval index * 4`:`eval index * 4 + 4`}"
    done
    # echo $CHAR
done

for out in ${OUTPUT[@]}; do
    echo $out
done
