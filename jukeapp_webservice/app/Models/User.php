<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class User extends Model
{
    use HasFactory;

    public $table = "users";

    public $timestamps = false;

    protected $fillable = [
        'id',
        'user_nick',
        'user_email',
        'user_password',
        'user_tokens'
    ];
}
