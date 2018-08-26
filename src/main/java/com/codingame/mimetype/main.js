/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

let N = parseInt(readline()); // Number of elements which make up the association table.
let Q = parseInt(readline()); // Number Q of file names to be analyzed.
let types = {};
for (var i = 0; i < N; i++) {
    var inputs = readline().split(' ');
    var EXT = inputs[0].toLowerCase(); // file extension
    var MT = inputs[1]; // MIME type.
    // print(EXT + " " + MT);
    types[EXT] = MT;
}
for (var i = 0; i < Q; i++) {
    var FNAME = readline().toLowerCase().trim(); // One file name per line.
    // print(FNAME);
    var dot = FNAME.lastIndexOf('.');
    if(dot >= 0 && types[FNAME.substring(dot + 1)] !== undefined) {
        print(types[FNAME.substring(dot + 1)]);
    } else {
        print("UNKNOWN");
    }
}
