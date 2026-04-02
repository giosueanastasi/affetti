import { Directive, Input, TemplateRef, ViewContainerRef, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthStateService } from './auth-state.service';

@Directive({
  selector: '[appHasRole]'
})
export class HasRoleDirective implements OnDestroy {
  private subscription: Subscription | null = null;
  private hasView = false;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private authState: AuthStateService
  ) {}

  @Input() set appHasRole(roles: string | string[]) {
    const roleArray = Array.isArray(roles) ? roles : [roles];
    
    this.subscription?.unsubscribe();
    this.subscription = this.authState.hasAnyRole$(roleArray).subscribe(hasRole => {
      if (hasRole && !this.hasView) {
        this.viewContainer.createEmbeddedView(this.templateRef);
        this.hasView = true;
      } else if (!hasRole && this.hasView) {
        this.viewContainer.clear();
        this.hasView = false;
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}