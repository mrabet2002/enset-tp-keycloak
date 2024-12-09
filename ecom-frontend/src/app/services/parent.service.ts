import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ParentService<T> {

  protected data$ = new BehaviorSubject<T | undefined>(undefined)

  constructor() { }

  observeStream() {
    return this.data$.asObservable()
  }

  notify(data: T) {
    this.data$.next(data)
  }

  notifyError(err: any) {
    console.error(err)
    this.data$.error(err)
  }

  /**
   * Clear the data stream
   */
  clear() {
    this.data$.next(undefined)
  }
}
