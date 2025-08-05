ChatMod - Version %%VER

Thank you for installing ChatMod on your server. This file provides information on how to complete the
config.yml, if you wish to change the settings.
After changing config.yml or blocked-words.yml, please do /chatmod reload to reload the config files.
There's also a list of permissions at the bottom of this file.
Please do not modify this file - any modifications will be deleted on server restart.

============config.yml=============

tests.caps-threshold: (default 80)
Sets the % of caps needed to block the message. The default value of 80 means over 80% caps.

tests.caps-minimum-length: (default 5)
Sets the minimum length of messages to check, preventing messages like "AAAA" from being flagged.
The default value of 5 means that messages under 5 characters long are ignored by this check.

tests.letter-spam-threshold: (default 6)
Sets the number of the same repeating letter that triggers this check.
The default value of 6 means that "aaaaa" passes this check, but "aaaaaa" fails it.

tests.spam-cooldown: (default 3)
Sets the speed of messages that trigger the spam filter (in seconds). The plugin internally operates
on a "3-strike model" for this, so sending a message within 3 seconds of the previous message 3 times
will trip this check and block further messages.

============blocked-words.yml=============

blocked-words.yml contains "regular expressions" to catch certain slurs, by default. If you wish to
add to this list, you can either create or search for regex that blocks the word you want to block,
or you can write it in plaintext with all versions you want to block. Eg. if you wanted to block the
word "fuck", you can either write regex to block it, or include all common variations like:
- "fuq"
- "fuuck"
- "fuuuck"
etc.
If it's flagging on words across boundaries (eg. if you've put "shit" into the blocked-words, you can
put \b before and after the word in the config to make sure it's only checking in word boundaries, eg:
- "\bshit\b"

============Permission reference=============

Each check can be controlled separately or all together.
chatmod.exempt:
This permission will exempt anyone who has it from **all** chat checks.
chatmod.exempt.caps:
This permission will exempt anyone who has it from the capslock check.
chatmod.exempt.letterspam:
This permission will exempt anyone who has it from the letterspam check.
chatmod.exempt.spam:
This permission will exempt anyone who has it from the spam check.
chatmod.exempt.word:
This permission will exempt anyone who has it from the blocked-words check.

And also:
chatmod.admin:
Use the /chatmod command. Please note, this permission does **not** grant chatmod.exempt, you need
to apply it separately.

==============================================

That's it! Thanks for reading.
-Asheiou