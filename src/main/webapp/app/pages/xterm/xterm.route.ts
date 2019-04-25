import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { XtermComponent } from 'app/pages/xterm/xterm.component';

export const xtermRoute: Routes = [
    {
        path: '',
        component: XtermComponent,
        canActivate: [UserRouteAccessService]
    }
];
