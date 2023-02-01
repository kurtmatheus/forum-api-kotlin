package br.com.navegapp.navegapi.util

 interface Mapper<T,U>{
     fun map(t: T): U
 }