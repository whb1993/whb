import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

// import { Terminal } from 'xterm';
// import { fit } from 'xterm/lib/addons/fit/fit';
// import style from 'xterm/dist/xterm.css';

import * as Terminal from 'xterm/dist/xterm';
import 'xterm/dist/addons/fit/fit.js';
@Component({
    selector: 'jhi-xterm',
    templateUrl: './xterm.component.html',
    styleUrls: ['./xterm.css']
})
export class XtermComponent implements OnInit {
    @ViewChild('myTerminal') terminalDiv: ElementRef;
    private term: Terminal;

    constructor() {}

    ngOnInit() {
        this.term = new Terminal();
        this.term.open(this.terminalDiv.nativeElement);
        this.term.write('Hello from \x1B[1;3;31mxterm.js\x1B[0m $ ');
    }
}
