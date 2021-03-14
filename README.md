# mNetwork
Minecraft staff plugin created for the now-shutdown mNetwork
Everything was logged into a MySQL database, so you'll have to fill in your information and compile the jar yourself.

```
Commands:
  clear chat (/cc)
    permission: staff.clearchat
  maintenance mode (/mm)
    permission: staff.mmode
  staff chat (!message)
    permission: staff.chat

Events:
  MaintenanceModeEvent (When /mm is ran)
      player - Who ran command
      isCancelled - If the command was cancelled (due to no perms, etc.)
      setCancelled(boolean) - Allow or deny the command 
      whitelist - Whether the whitelist was enabled or disabled
  StaffChatEvent (When !message is said)
      player - Who ran command
      isCancelled - If the command was cancelled (due to no perms, etc.)
      setCancelled(boolean) - Allow or deny the command 
      message - Message that was said 
```
    
      
