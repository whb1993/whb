import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVueUser } from 'app/shared/model/vue-user.model';
import { VueUserService } from './vue-user.service';

@Component({
    selector: 'jhi-vue-user-delete-dialog',
    templateUrl: './vue-user-delete-dialog.component.html'
})
export class VueUserDeleteDialogComponent {
    vueUser: IVueUser;

    constructor(protected vueUserService: VueUserService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vueUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vueUserListModification',
                content: 'Deleted an vueUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vue-user-delete-popup',
    template: ''
})
export class VueUserDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vueUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VueUserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.vueUser = vueUser;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/vue-user', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/vue-user', { outlets: { popup: null } }]);
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
