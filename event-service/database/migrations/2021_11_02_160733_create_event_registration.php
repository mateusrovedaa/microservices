<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEventRegistration extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('events', function (Blueprint $table) {
            $table->id();
            $table->string('description');
            $table->date('event_date');
        });

        Schema::create('event_registrations', function (Blueprint $table) {
            $table->id();
            $table->boolean('checkin');
            $table->boolean('activated');
            $table->boolean('email_sent');
            $table->string('certificate');
            $table->string('user_email');
            $table->foreign('user_email')->references('email')->on('users');
            $table->unsignedBigInteger('event_id');
            $table->foreign('event_id')->references('id')->on('events');
            $table->timestamps();
            $table->unique(['user_email', 'event_id']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('events');
        Schema::dropIfExists('event_registration');
    }
}
