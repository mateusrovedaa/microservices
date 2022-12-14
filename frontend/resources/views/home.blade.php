@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ __('Dashboard') }}</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif
                    @if($errors->any())
                        <div class="alert alert-danger">
                            {{ implode('', $errors->all(':message')) }}
                        </div>
                    @endif

                    <a href="/list-events"><button class="btn btn-secondary">Events</button></a>

                    <a href="/list-inscriptions"><button class="btn btn-secondary">My inscriptions</button></a>

                    <a href="/update"><button class="btn btn-secondary">My data</button></a>
                </div>

            </div>
        </div>
    </div>
</div>
@endsection
