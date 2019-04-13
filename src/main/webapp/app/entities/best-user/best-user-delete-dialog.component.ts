import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBestUser } from 'app/shared/model/best-user.model';
import { BestUserService } from './best-user.service';

@Component({
    selector: 'jhi-best-user-delete-dialog',
    templateUrl: './best-user-delete-dialog.component.html'
})
export class BestUserDeleteDialogComponent {
    bestUser: IBestUser;

    constructor(protected bestUserService: BestUserService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bestUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bestUserListModification',
                content: 'Deleted an bestUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-best-user-delete-popup',
    template: ''
})
export class BestUserDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bestUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BestUserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.bestUser = bestUser;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/best-user', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/best-user', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
