@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Update category</div>

                <div class="card-body">
                    <form method="POST" action="/update-category">
                        @csrf
                        @method('PUT')
                        <div class="form-group row">
                            <label for="name" class="col-md-4 col-form-label text-md-right">Category name</label>

                            <div class="col-md-6">
                                <input type="hidden" name="id" value="{{$category['id']}}">
                                <input type="text" name="name" value="{{$category['name']}}">
                            </div>
                        </div>
                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-4">
                                <button type="submit" class="btn btn-primary">Send</button>
                                <a href="{{ url('list-categories') }}"><button type="button" class="btn btn-secondary">Cancel</button></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection