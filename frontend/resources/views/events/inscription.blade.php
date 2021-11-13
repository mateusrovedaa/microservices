@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Inscription in an event</div>

                <div class="card-body">
                    <form method="POST" action="/inscription">
                        @csrf

                        <div class="form-group row">
                            @foreach($jsonevents['data'] as $event)
                            <div class="col-md-6">
                                <input type="hidden" name="user_email" value="{{Session::get('email')}}">
                                <input type="hidden" name="event_id" value="{{$event['id']}}">
                                <input type="hidden" name="checkin" value="0">
                            </div>
                        </div>
                            <div class="form-group row">
                                <label for="description" class="col-md-4 col-form-label text-md-right">Description</label>

                                <div class="col-md-6">
                                <input type="text" name="event_name" class="col-md-6" value="{{$event['description']}}" readonly>
                            </div>
                            @endforeach
                        </div>
                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-4">
                                <button type="submit" class="btn btn-primary">Send</button>
                                <a href="{{ url('list-events') }}"><button type="button" class="btn btn-secondary">Cancel</button></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
