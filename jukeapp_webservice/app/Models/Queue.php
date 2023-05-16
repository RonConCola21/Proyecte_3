<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Queue extends Model
{
    use HasFactory;

    public $table = "queue";

    public $timestamps = false;

    protected $fillable = [
        'id',
        'que_son_id',
        'que_users_id',
        'que_moment_temp',
    ];
}
