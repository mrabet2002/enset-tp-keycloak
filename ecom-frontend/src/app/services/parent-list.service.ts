import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {ParentService} from "./parent.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ParentListService<T> extends ParentService<T[]> {

  constructor() {
    super()
  }

  /**
   * Add data to the list
   * @param data
   */
  add(data: T) {
    const currentData = this.data$.value;
    if (!currentData) {
      this.notify([data]);
      return;
    }
    const updatedData = [...currentData, data];
    this.notify(updatedData);
  }

  /**
   * Remove data from the list
   * @param filterValue
   * @param filterKey
   */
  remove(filterValue: any, filterKey: keyof T) {
    const currentData = this.data$.value;
    if (!currentData) return;
    const updatedData = currentData.filter(data => data[filterKey] !== filterValue);
    this.notify(updatedData);
  }
}
