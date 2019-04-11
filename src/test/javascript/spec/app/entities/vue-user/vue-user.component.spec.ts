/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { WhbTestModule } from '../../../test.module';
import { VueUserComponent } from 'app/entities/vue-user/vue-user.component';
import { VueUserService } from 'app/entities/vue-user/vue-user.service';
import { VueUser } from 'app/shared/model/vue-user.model';

describe('Component Tests', () => {
    describe('VueUser Management Component', () => {
        let comp: VueUserComponent;
        let fixture: ComponentFixture<VueUserComponent>;
        let service: VueUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WhbTestModule],
                declarations: [VueUserComponent],
                providers: []
            })
                .overrideTemplate(VueUserComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VueUserComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VueUserService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new VueUser(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.vueUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
