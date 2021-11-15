@extends('layouts.app')
@section('content')
<style>
    .container {
        position: relative;
        text-align: center;
        color: white;
    }

    /* Centered text */
    .centered {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        color: black;
    }

    /* Centered text */
    .centered2 {
        position: absolute;
        top: 45%;
        left: 50%;
        transform: translate(-50%, -50%);
        color: black;
    }

    /* Centered text */
    .centered3 {
        position: absolute;
        top: 55%;
        left: 50%;
        transform: translate(-50%, -50%);
        color: black;
    }
</style>
@if(!empty($jsoncertificate['data']))
<div class="container" align="center">
    <img width="1024" height="650" src="{{ asset('images/certificate.jpg') }}">
    <div class="centered2">Name: {{ $jsonuser['data'][0]['name'] }}</div>
    <div class="centered">Certificate: {{ $jsoncertificate['data']['certificate'] }}</div>
    <div class="centered3">Event: {{ $jsonevent['data'][0]['description'] }}</div>
</div>
@else
<div class="centered">Certificate invalid</div>
@endif
@endsection
