package br.com.alura.forum.util

 interface Mapper<T,U>{
     fun map(t: T): U
 }