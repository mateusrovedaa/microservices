@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Create entry</div>

                <div class="card-body">
                    <form method="POST" action="/save-entry">
                        @csrf
                        <div class="form-group row">
                            <label for="value" class="col-md-4 col-form-label text-md-right">Value</label>

                            <div class="col-md-6">
                                <input type="hidden" name="user_id" value=" {{ Auth::user()->id }}">
                                <input type="number" placeholder="0.00" min="0" step="0.01" name="value" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="payment_date" class="col-md-4 col-form-label text-md-right">Payment Date</label>

                            <div class="col-md-6">
                                <input type="date" name="payment_date" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="status" class="col-md-4 col-form-label text-md-right">Status</label>

                            <div class="col-md-6">
                                <input type="text" name="status" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="description" class="col-md-4 col-form-label text-md-right">Description</label>

                            <div class="col-md-6">
                                <input type="text" name="description" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="status" class="col-md-4 col-form-label text-md-right">Entry type</label>

                            <div class="col-md-6">
                                <select class="form-control" id="entry_type_id" name="entry_type_id">
                                    @foreach($entrytypes as $entrytype)
                                        <option value="{{ $entrytype->id }}">{{$entrytype->name }}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="status" class="col-md-4 col-form-label text-md-right">Category</label>

                            <div class="col-md-6">
                                <select class="form-control" id="category_id" name="category_id">
                                    @foreach($categories as $category)
                                        <option value="{{ $category->id }}">{{$category->name }}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-4">
                                <button type="submit" class="btn btn-primary">Send</button>
                                <a href="{{ url('list-entries') }}"><button type="button" class="btn btn-secondary">Cancel</button></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection