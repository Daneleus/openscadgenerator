difference(){cube(size=[200.0000,200.0000,20.0000],center=true);union(){cylinder(h=100.0000,d=50.0000,$fn=64);translate([80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,-80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}translate([-80.0,80.0,0.0]){cylinder(h=100.0000,d=10.0000,$fn=64);}}}