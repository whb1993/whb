import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';

// import { Terminal } from 'xterm';
// import { fit } from 'xterm/lib/addons/fit/fit';
// import style from 'xterm/dist/xterm.css';

import * as Terminal from 'xterm/dist/xterm';
import 'xterm/dist/addons/fit/fit.js';
import { ZTrackerService } from 'app/zpages/core/tracker/tracker.service';
@Component({
    selector: 'jhi-xterm',
    templateUrl: './xterm.component.html',
    styleUrls: ['./xterm.css']
})
export class XtermComponent implements OnInit, OnDestroy {
    @ViewChild('myTerminal') terminalDiv: ElementRef;
    private term: Terminal;
    zMes: string;
    mesArr: string[] = [];
    constructor(private zTrackerService: ZTrackerService) {}

    ngOnInit() {
        // this.term = new Terminal();
        // this.term.open(this.terminalDiv.nativeElement);
        // this.term.write('Hello from \x1B[1;3;31mxterm.js\x1B[0m $ ');
        this.subscribeMes();
    }
    sendMes() {
        this.zTrackerService.sendMes(this.zMes);
    }
    subscribeMes() {
        this.zTrackerService.subscribeMes();
        this.zTrackerService.receive().subscribe(data => {
            console.log(data);
            this.mesArr.push(JSON.stringify(data));
        });
    }

    ngOnDestroy() {
        this.zTrackerService.unSubscribeMes();
    }
    clear() {
        this.mesArr = [];
    }
}
