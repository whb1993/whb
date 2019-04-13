/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { WhbTestModule } from '../../../test.module';
import { BestUserComponent } from 'app/entities/best-user/best-user.component';
import { BestUserService } from 'app/entities/best-user/best-user.service';
import { BestUser } from 'app/shared/model/best-user.model';

describe('Component Tests', () => {
    describe('BestUser Management Component', () => {
        let comp: BestUserComponent;
        let fixture: ComponentFixture<BestUserComponent>;
        let service: BestUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WhbTestModule],
                declarations: [BestUserComponent],
                providers: []
            })
                .overrideTemplate(BestUserComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BestUserComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BestUserService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BestUser(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.bestUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
