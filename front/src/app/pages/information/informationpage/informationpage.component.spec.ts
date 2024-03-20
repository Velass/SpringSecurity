import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InformationpageComponent } from './informationpage.component';

describe('InformationpageComponent', () => {
  let component: InformationpageComponent;
  let fixture: ComponentFixture<InformationpageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InformationpageComponent]
    });
    fixture = TestBed.createComponent(InformationpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
